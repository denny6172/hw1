/* 413226087 李柏翰 */
/* 413085043 謝慶章 */

import java.util.*;

public interface Bool {
    //做proxy的介面
    public boolean removeMember(String uid, String pwd);
    public boolean isMemberInfoCorrect(String phone);
    public boolean isValidityMember(String cardNumber);
    public boolean isAlreadyBind(String cardNumber);
    public boolean isAlreadyRental(String cardNumber);
}
