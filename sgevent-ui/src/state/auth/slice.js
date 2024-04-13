import { createSlice } from "@reduxjs/toolkit";
import { authApi } from "../../services/auth.service";

// 定义Slice名称
export const authSliceName = "auth";

// 创建Slice
const authSlice = createSlice({
  name: authSliceName,
  initialState: {
    userInfo: {}, // 初始时没有用户信息
    isLoggedIn: false, // 初始时用户未登录
  },
  reducers: {

    // 用户登出
    logout: (state) => {
      // 重置状态为初始值
      state.userInfo = {};
      state.isLoggedIn = false;
    },
    // 可以根据需要添加其他reducers
  },
  extraReducers: (builder) => {
    // 通过RTK Query处理登录/注册成功
    builder
      .addMatcher(
        authApi.endpoints.login.matchFulfilled,
        (state, { payload }) => {
          // 假设payload直接包含用户信息
          state.isLoggedIn = true;
          state.userInfo = payload; // 直接将userInfo设置为payload
        }
      )
      .addMatcher(
        authApi.endpoints.signUp.matchFulfilled,
        (state, { payload }) => {
          state.isLoggedIn = true;
          state.userInfo = payload; // 注册成功也是同样处理
        }
      );
  },
});

// 导出actions和reducer
export const { setCredentials, setSignUp, logout } = authSlice.actions;
export default authSlice.reducer;

// 导出selectors
export const authSelector = (state) => state[authSliceName];
export const userNameSelector = (state) =>
  state[authSliceName].userInfo?.userName;
