package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();
        Blog blog = blogRepository2.findById(blogId).get();

        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);
        blog.getImageList().add(image);
        blogRepository2.save(blog);

        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).get();
        String dimension = image.getDimensions();

        int xi=0, yi=0;
        int xs=0, ys=0;
        int num=0;

        for(int i=0; i<dimension.length(); i++){
            if(dimension.charAt(i)=='X'){
                xi=num;
                num=0;
                continue;
            }
            num *= 10;
            num += (dimension.charAt(i)-'0');
        }
        yi=num;

        num=0;
        for(int i=0; i<screenDimensions.length(); i++){
            if(screenDimensions.charAt(i)=='X'){
                xs=num;
                num=0;
                continue;
            }
            num *= 10;
            num += (screenDimensions.charAt(i)-'0');
        }
        ys=num;

        int ans = (int) (Math.floor((new Double(xs))/(new Double(xi))) * Math.floor((new Double(ys))/(new Double(yi))));

        return ans;
    }
}
