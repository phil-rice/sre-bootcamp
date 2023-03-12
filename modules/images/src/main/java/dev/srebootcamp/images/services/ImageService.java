package dev.srebootcamp.images.services;

import dev.srebootcamp.images.domain.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static dev.srebootcamp.images.utils.JdbcHelper.getFromIds;

@Service
@Component
public class ImageService implements IImageService {
    JdbcTemplate template = new JdbcTemplate();
    static final String allSql = "SELECT * FROM images where compoundId = ?";
    static final String someSql = "SELECT * FROM images WHERE compoundId = ? and id IN (<ids>)";

    static final RowMapper<Image> imageRowMapper =
            (rs, rowNum) -> new Image(rs.getString("compoundId"), rs.getString("url"), rs.getString("id"));

    public List<Image> getAllImages(String compoundId) {
        PreparedStatementSetter setCompound = ps -> ps.setString(1, compoundId);
        return template.query(allSql, setCompound, imageRowMapper);
    }

    public List<Image> getImages(String compoundId, List<String> ids) {
        return getFromIds(template, someSql, List.of(compoundId), ids, imageRowMapper);
    }

    public Optional<Image> getImage(String compoundId, String id) {
        return getImages(compoundId, List.of(id)).stream().findFirst();
    }
}
