import { createSlice } from "@reduxjs/toolkit";
import { authApi } from "../../services/auth.service";

const initialState = {
  isLoggedin: false,
  userInfo: {},
};

export const authSliceName = "authSlice";

const authSlice = createSlice({
  name: authSliceName,
  initialState,
  reducers: {
    // 更新登录状态
    setLoginStatus: (state, action) => {
      const { payload } = action;
      state.isLoggedin = payload;
    },
    // 新增：设置用户信息
    setCredentials: (state, action) => {
      const { user_info, isLoggedin } = action.payload;
      state.userInfo = user_info; // 假设payload包含用户信息
      state.isLoggedin = isLoggedin; // 可以同时设置登录状态
    },
    logout: (state) => {
      state.user_info = {};
      state.isLoggedin = false;
    },

  },
  extraReducers: (builder) => {
    builder.addMatcher(
      authApi.endpoints.login.matchFulfilled,
      (state, { payload }) => {
        state.isLoggedin = true;
        state.userInfo = payload.data; // 假设payload.data包含了需要的用户信息
      }
    );
  },
});

export const { setLoginStatus, setCredentials, logout } = authSlice.actions;

export default authSlice.reducer;

export const authSelector = (state) => state?.[authSliceName];
