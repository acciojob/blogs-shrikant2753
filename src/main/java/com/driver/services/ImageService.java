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
        Image image = imageRepository2.findById(id).get();
        imageRepository2.deleteById(id);
        Blog blog = blogRepository2.findById(id).get();
        List<Image> imageList = blog.getImageList();

        for(Image image1 : imageList){
            if(image1.equals(image)){
                imageList.remove(image1);
                break;
            }
        }
        blogRepository2.save(blog);
        return;
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        return 0;
    }
}
