package com.tinder;

import com.entity.Profile;
import com.entity.impl.LikedProfilesImpl;
import com.entity.impl.ProfileImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LikePageController", urlPatterns = {"/like-page"})
public class LikePageController extends HttpServlet {
    private static int pictureID = 0;
    private final ProfileImpl profileImpl = new ProfileImpl();
    private final LikedProfilesImpl likedProfileIml = new LikedProfilesImpl();

    public int getPictureID() {
        return pictureID;
    }

    public void setPictureID(int pictureID) {
        int size = profileImpl.getAll().size();
        if (pictureID >= size) {
            LikePageController.pictureID = 0;
        } else {
            LikePageController.pictureID = pictureID;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Profile> profiles = profileImpl.getAll();
        String likeButton = request.getParameter("likeButton");
        String dislikeButton = request.getParameter("dislikeButton");
        String url = request.getParameter("picture");
        if (likeButton != null) {
            likedProfile(profiles.get(getPictureID()));
            setPictureID(getPictureID() + 1);
        } else if (dislikeButton!=null){
            System.out.println("in dislike");
            System.out.println(getPictureID());
            setPictureID(getPictureID()+1);
        }
        response.sendRedirect("like-page.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void likedProfile(Profile profile) {
        likedProfileIml.addProfile(profile);
        profileImpl.removeProfile(profile);
    }
}