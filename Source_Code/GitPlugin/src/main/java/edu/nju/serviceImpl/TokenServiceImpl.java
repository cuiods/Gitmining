package edu.nju.serviceImpl;

import edu.nju.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * random select a token
 * @author cuihao
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Value("#{configProperties['token1']}")
    private String token1;

    @Value("#{configProperties['token2']}")
    private String token2;

    @Value("#{configProperties['token3']}")
    private String token3;

    @Value("#{configProperties['token4']}")
    private String token4;

    @Override
    public String getApiToken() {
        String[] tokens = new String[]{token1, token2, token3, token4};
        double random = Math.random()*tokens.length;
        return tokens[(int)random];
    }
}
