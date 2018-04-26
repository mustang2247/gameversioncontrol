package com.open.coinnews.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Yanyanbing
 * @date 2018-04-18 15:59
 */
public class UserLoginStatu {

    public static Object loginStatu(HttpServletRequest request){

        try {
            MySessionContext myc= MySessionContext.getInstance();
            HttpSession sess = myc.getSession(request.getSession().getId());
            if(sess == null){
                return null;
            }else{
                return sess;
            }
        }catch (Exception e){
            return null;
        }

    }
}
