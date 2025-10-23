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

    private FournisseurService fournisseurservice;
    private final Gson gson = new Gson();


    public FournisseurController(){}

    public FournisseurController(FournisseurService fournisseurservice){
        this.fournisseurservice = fournisseurservice;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        response.setContentType("application/json");
        String method = request.getMethod();
        String path = request.getPathInfo();

        if ("GET".equalsIgnoreCase(method) && (path == null || "/v1/fournisseurs".equals(path))) {
            List<Fournisseur> fournisseurs = fournisseurservice.findAll();
            if (fournisseurs == null || fournisseurs.isEmpty()) {
                response.getWriter().write("{\"error\":\"fournisseurs list is empty\"}");
            } else {
                response.getWriter().write(gson.toJson(fournisseurs));
            }

        } else if ("GET".equalsIgnoreCase(method) && path.matches("/v1/fournisseurs/\\d+")) {
            Long id = Long.parseLong(path.substring(path.lastIndexOf('/') + 1));
            Fournisseur fournisseur = fournisseurservice.findById(id);
            if (fournisseur == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\":\"fournisseur not found\"}");
            } else {
                response.getWriter().write(gson.toJson(fournisseur));
            }

        } else if ("POST".equalsIgnoreCase(method)) {
            Fournisseur s = gson.fromJson(readBody(request), Fournisseur.class);
            fournisseurservice.save(s);
            response.setStatus(HttpServletResponse.SC_CREATED);

        } else if ("PUT".equalsIgnoreCase(method) && path != null && path.matches("/v1/fournisseurs/\\d+")) {
            Long id = Long.parseLong(path.substring(path.lastIndexOf('/') + 1));
            Fournisseur s = gson.fromJson(readBody(request), Fournisseur.class);
            fournisseurservice.update(id, s);
            response.setStatus(HttpServletResponse.SC_OK);

        } else if ("DELETE".equalsIgnoreCase(method) && path != null && path.matches("/v1/fournisseurs/\\d+")) {
            Long id = Long.parseLong(path.substring(path.lastIndexOf('/') + 1));
            fournisseurservice.delete(id);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"error\":\"Endpoint not found\"}");
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
