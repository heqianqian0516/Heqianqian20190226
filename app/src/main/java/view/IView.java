package view;

public interface IView<T> {
    void requestDataSuccess(T t);
    void requestDataFail(String error);
}
