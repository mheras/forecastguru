package com.forecastGuru.presenter;

import org.springframework.stereotype.Component;

import com.forecastGuru.web.CustomSession;
import com.forecastGuru.web.view.ISignInView;

/**
 * Presenter for the login.
 * 
 * @author Martin A. Heras
 * 
 */
@Component(value = "loginPresenter")
public class SignInPresenter implements ISignInPresenter {

	/* (non-Javadoc)
	 * @see com.forecastGuru.presenter.ISignInPresenter#signIn(com.forecastGuru.web.view.ISignInView)
	 */
	public boolean signIn(ISignInView loginView) {
		return CustomSession.get().signIn(loginView.getUserName(), loginView.getPassword());
	}
}