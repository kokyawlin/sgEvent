import React, { useState } from 'react';
import { navigate } from 'gatsby';
import { TextField, Button, Grid, Typography } from '@mui/material';
import { useSignUpMutation } from '../services/auth.service'; // 确保导入路径正确
import { setSignUp } from '../state/auth/slice';

export default function SignUpPage() {
  const [emailAddress, setEmailAddress] = useState('');
  const [password, setPassword] = useState('');
  const [signUp, { isLoading, isError }] = useSignUpMutation(); // 假设您已经在auth.service中定义了这个mutation

  const handleSignUp = async () => {
    try {
      const userData = await signUp({ emailAddress, password }).unwrap();
      // 注册成功后更新 Redux 状态
      setSignUp(userData);
      navigate('/login'); // 注册成功后导航到登录页面
    } catch (error) {
      console.error('Failed to sign up:', error);
    }
  };

  return (
    <Grid container spacing={2} justifyContent="center" style={{ minHeight: '100vh' }}>
      <Grid item xs={12}>
        <Typography variant="h4" textAlign="center">Sign Up</Typography>
      </Grid>
      <Grid item xs={12} md={6}>
        <TextField
          fullWidth
          label="Email Address"
          variant="outlined"
          value={emailAddress}
          onChange={(e) => setEmailAddress(e.target.value)}
          margin="normal"
        />
      </Grid>
      <Grid item xs={12} md={6}>
        <TextField
          fullWidth
          label="Password"
          variant="outlined"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          margin="normal"
        />
      </Grid>
      <Grid item xs={12}>
        <Button
          variant="contained"
          color="primary"
          onClick={handleSignUp}
          disabled={isLoading}
        >
          Sign Up
        </Button>
        {isError && <Typography color="error">Sign up failed. Please try again.</Typography>}
      </Grid>
    </Grid>
  );
}
