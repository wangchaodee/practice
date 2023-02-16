package com.iflytek.staff.chao.structure.scene;

import java.util.*;

/**
 * @author : wangchaodee
 * @Description: 1797. 设计一个验证系统
 * @date Date : 2023年02月15日 09:16
 */
public class AuthenticationManager {

    int timeToLive ;
    Map<String ,Integer> tokenMap ;

    public AuthenticationManager(int timeToLive) {
        this.timeToLive = timeToLive;
        tokenMap = new HashMap<>();
    }

    public void generate(String tokenId, int currentTime) {
        add(tokenId,currentTime);
    }

    public void renew(String tokenId, int currentTime) {
        if(tokenMap.containsKey(tokenId)){
            if(currentTime >= tokenMap.get(tokenId)){
                remove(tokenId);
            }else {
                add(tokenId,currentTime);
            }
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        int cnt =0  ;
        List<String> tokenIds = new ArrayList<>();
        for (Map.Entry<String,Integer> entry : tokenMap.entrySet()){
            if(currentTime >= entry.getValue()){
                tokenIds.add(entry.getKey());
            }else {
                cnt++;
            }
        }
        remove(tokenIds);
        return cnt;
    }

    public int countUnexpiredTokens2(int currentTime) {
        Iterator<Map.Entry<String,Integer> > iterator = tokenMap.entrySet().iterator() ;
        while (iterator.hasNext()){
            Map.Entry<String,Integer> entry = iterator.next();
            if(currentTime >= entry.getValue()){
                iterator.remove();
            }
        }
        return tokenMap.size();
    }

    private void add(String tokenId, int currentTime){
        tokenMap.put(tokenId , currentTime+timeToLive);
    }
    private void remove(List<String> tokenIds){
        for(String tokenId : tokenIds){
            remove(tokenId);
        }
    }
    private void remove(String tokenId){
        tokenMap.remove(tokenId);
    }

}
