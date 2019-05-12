package com.forbitbd.quizapp.ui.main.profile;


import com.forbitbd.quizapp.model.User;

public interface ProfileContract {

    interface Presenter{
        void requestForUser();
        boolean validate(User user);
        void updateUser(User user, byte[] uri);
        void uploadUserImage(byte[] bytes);
        void browseClick();
    }

    interface View{
        void renderUI(User user);
        void updateUser(User user);

        void clearPreErrors();
        void showDialog();
        void hideDialog();
        void showErrorMessage(int fieldId, String message);
        void complete(String url);
        void basicUpdateComplete();
        void openCropImageActivity();
    }
}
