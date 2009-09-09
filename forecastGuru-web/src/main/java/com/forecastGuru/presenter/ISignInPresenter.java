package com.forecastGuru.presenter;

import com.forecastGuru.web.view.ISignInView;

/**
 * Login presenter interface.
 * 
 * @author Martin A. Heras
 * 
 */
public interface ISignInPresenter extends IPresenter {
	public boolean signIn(ISignInView loginView);
}
