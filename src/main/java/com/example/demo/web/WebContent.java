package com.example.demo.web;

import com.example.demo.database.UseForUser;
import com.example.demo.database.User;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.documents.BuiltinStyle;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.ParagraphStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.InputStream;


@RestController
public class WebContent {
    private long id = 0;
    protected String filePath = "./listUsers.docx";

    @Autowired
    UseForUser users;

    @Autowired
    ServletContext context;

    @GetMapping("/create/{name}")
    public String indexAdd(@PathVariable String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        users.save(user);
        id++;

        /*  Create doc  */
        Document document = new Document();
        Section section = document.addSection();
        Paragraph subheading_1 = section.addParagraph();
        subheading_1.appendText("List all users -");
        Paragraph para_1 = section.addParagraph();
        para_1.appendText(users.findAll().toString());
        subheading_1.applyStyle(BuiltinStyle.Heading_3);
        ParagraphStyle style = new ParagraphStyle(document);
        style.setName("paraStyle");
        style.getCharacterFormat().setFontName("Arial");
        style.getCharacterFormat().setFontSize(11f);
        document.getStyles().add(style);
        para_1.applyStyle("paraStyle");
        document.saveToFile(context.getRealPath(filePath), FileFormat.Docx);

        return "Create and add user with name - " + name;
    }

    /*  DON'T WORK  */
    @GetMapping("/deleteid/{id}")
    public String indexDeleteById(@PathVariable Long aId) {
        for (User user : users.findAll()) {
            if (aId.equals(user.getId())) {
                String timeName = user.getName();
                long timeId = user.getId();
                users.delete(user);
                replaceId();
                return "Delete user with name - " + timeName + " and id " + timeId;
            }
        }
        return "You made a mistake with the id ";
    }

    @GetMapping("/deletename/{name}")
    public String indexDeleteByName(@PathVariable String name) {
        for (User user : users.findAll()) {
            if (name.equals(user.getName())) {
                String timeName = user.getName();
                long timeId = user.getId();
                users.delete(user);
                replaceId();
                return "Delete user with name - " + timeName + " and id " + timeId;
            }
        }
        return "You made a mistake with the name ";
    }

    private void replaceId() {
        id = 1;
        for (User user : users.findAll()) {
            user.setId(id);
            id++;
        }
    }

    @GetMapping("/show")
    public String indexForAll() {
        return "\n\n"
                + "   ALL USERS   - "
                + "\n\n"
                + users.findAll();
    }

    /*"/file/listUsers.docx"*/
    @RequestMapping(value = "/listUsers.docx", method = RequestMethod.GET)
    public ResponseEntity<Object> indexExport() {
        InputStream inputStream = context.getResourceAsStream(filePath);
        assert inputStream != null;
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .body(new InputStreamResource(inputStream));
    }

    @RequestMapping("*")
    public String indexAll() {
        return "WORKING!!!";
    }
}
