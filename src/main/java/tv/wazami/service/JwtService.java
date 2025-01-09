package tv.wazami.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class JwtService {

    private final JdbcTemplate jdbcTemplate;

    public JwtService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> fetchAndPrintRecords(String token) {
        String sql = "SELECT * FROM jwt_token WHERE jwt = '" + token + "' AND status = 1 ORDER BY id DESC LIMIT 1";

        // Correct use of List and Map with generics
        List<Map<String, Object>> records = jdbcTemplate.queryForList(sql);
        
        if(!records.isEmpty()) {
        	return records;
        }
        
        return null;
    }
}
