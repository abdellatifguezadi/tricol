package org.tricol.controller;

import com.google.gson.Gson;
import org.tricol.model.Fournisseur;
import org.tricol.service.FournisseurService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import java.io.BufferedReader;
import java.util.List;

public class FournisseurController extends AbstractController {

    private FournisseurService fournisseurServices;
    private final Gson gson = new Gson();

    public FournisseurController() {
        setSupportedMethods(new String[]{"GET", "POST", "PUT", "DELETE", "HEAD"});
    }

    public FournisseurController(FournisseurService fournisseurServices){
        this();
        this.fournisseurServices = fournisseurServices;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        response.setContentType("application/json");
        String method = request.getMethod();
        String path = request.getPathInfo();

        try {
            if ("GET".equalsIgnoreCase(method) && (path == null || "/v1/fournisseurs".equals(path))) {
                List<Fournisseur> fournisseurs = fournisseurServices.findAll();
                if (fournisseurs == null || fournisseurs.isEmpty()) {
                    response.getWriter().write("{\"error\":\"Fournisseurs list is empty\"}");
                } else {
                    response.getWriter().write(gson.toJson(fournisseurs));
                }

            } else if ("GET".equalsIgnoreCase(method) && path.matches("/v1/fournisseurs/\\d+")) {
                Long id = Long.parseLong(path.substring(path.lastIndexOf('/') + 1));
                Fournisseur supplier = fournisseurServices.findById(id);
                if (supplier == null) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\":\"Fournisseur not found\"}");
                } else {
                    response.getWriter().write(gson.toJson(supplier));
                }

            } else if ("POST".equalsIgnoreCase(method) && "/v1/fournisseurs".equals(path)) {
                Fournisseur s = gson.fromJson(readBody(request), Fournisseur.class);
                fournisseurServices.save(s);
                response.setStatus(HttpServletResponse.SC_CREATED);
                response.getWriter().write("{\"message\":\"Fournisseur created successfully\"}");

            } else if ("PUT".equalsIgnoreCase(method) && path.matches("/v1/fournisseurs/\\d+")) {
                Long id = Long.parseLong(path.substring(path.lastIndexOf('/') + 1));
                Fournisseur s = gson.fromJson(readBody(request), Fournisseur.class);
                fournisseurServices.update(id, s);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("{\"message\":\"Fournisseur updated successfully\"}");

            } else if ("DELETE".equalsIgnoreCase(method) && path.matches("/v1/fournisseurs/\\d+")) {
                Long id = Long.parseLong(path.substring(path.lastIndexOf('/') + 1));
                fournisseurServices.delete(id);
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);

            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\":\"Endpoint not found\"}");
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Invalid ID format\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Internal server error: " + e.getMessage() + "\"}");
        }

        return null;
    }

    private String readBody(HttpServletRequest request) throws Exception {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) sb.append(line);
        }
        return sb.toString();
    }
}
