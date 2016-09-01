package ua.testapp.phonebook.interfaces;

public interface TaskCompleteListener<T> {
    void onTaskComplete(T result);
    void onFail();
}
