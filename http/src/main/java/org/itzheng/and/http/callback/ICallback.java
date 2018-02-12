package org.itzheng.and.http.callback;

/**
 * Title:<br>
 * Description: <br>
 *
 * @email ItZheng@ZoHo.com
 * Created by itzheng on 2018-2-7.
 */
public interface ICallback {
    void onSuccess(String response);

    void onError(String error, Exception e);
}
