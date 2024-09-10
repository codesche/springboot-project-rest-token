package com.kosta.service;

import java.util.List;

import com.kosta.domain.*;

public interface AuthService {

	UserResponse signUp(SignUpRequest signUpRequest);

	List<UserResponse> getUserList();

	UserResponse updateUser(UserUpdateRequest userUpdateReqeust);

	void deleteUser(UserDeleteRequest userDeleteRequest);

	boolean duplicateCheckEmail(String email);

//    LoginResponse login(String email, String password);
}
