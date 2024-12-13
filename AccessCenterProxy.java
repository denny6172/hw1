/* 413226087 李柏翰 */
/* 413085043 謝慶章 */

import java.util.*;

public class AccessCenterProxy implements Bool{

    //將判斷的地方都交由這裡去判斷 判斷成功後會跳回原本的地方繼續執行動作
    public DatabaseConnect db = new DatabaseConnect();
    AccessCenter accesscenter=new AccessCenter();
    public boolean removeMember(String uid, String pwd){
        if(db.removeMember(uid, pwd)){
            accesscenter.removeMember(uid,pwd); //判斷成功後會跳回原本的地方繼續執行動作
            return true;
        }
        return false;   //判斷失敗後 不會繼續後續動作
    }
    public boolean isMemberInfoCorrect(String phone){
        return true;
    }
    public boolean isValidityMember(String cardNumber){
        if(db.checkMember(cardNumber) == true) {
            accesscenter.isValidityMember(cardNumber);  //判斷成功後會跳回原本的地方繼續執行動作
            return true;
        }
        else
            return false;   //判斷失敗後 不會繼續後續動作
    }
    public boolean isAlreadyBind(String cardNumber){
        DatabaseConnect databaseConnect = new DatabaseConnect();
        if (databaseConnect.checkInfo(cardNumber) == true){
            accesscenter.isAlreadyBind(cardNumber);     //判斷成功後會跳回原本的地方繼續執行動作
            return true;
        }
        else
            return false;   //判斷失敗後 不會繼續後續動作
    }
    public boolean isAlreadyRental(String cardNumber){
        if (db.checkRentState(cardNumber)) {
            accesscenter.isAlreadyRental(cardNumber);   //判斷成功後會跳回原本的地方繼續執行動作
            return true;
        }
        else
            return false;   //判斷失敗後 不會繼續後續動作
    }
}

