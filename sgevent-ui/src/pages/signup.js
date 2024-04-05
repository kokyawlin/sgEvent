import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { Grid, Typography, Button, TextField } from '@mui/material';
import { useSignUpMutation } from '../services/auth.service'; // 确保这个导入路径是正确的
import { navigate } from 'gatsby';
 
export default function SignUpPage() {
    const dispatch = useDispatch();
    const [emailAddress, setEmailAddress] = useState('');
    const [password, setPassword] = useState('');
    const [signUp, { isLoading, isError }] = useSignUpMutation();
 
    const onSignUp = async () => {
        try {
            const userData = await signUp({
              emailAddress: emailAddress,
              password: password,
            }).unwrap();
            navigate('/login'); // 如果注册成功，重定向到登录页面
        } catch (err) {
            console.error('Failed to sign up:', err);
        }
    };
 
    return (
<Grid
        container
        spacing={2}
        justifyContent="center"
        alignItems="center"
        style={{ minHeight: '100vh' }}
>
<Grid item xs={12}>
<Typography variant="h4" textAlign="center">
            Welcome to Community Event Center
</Typography>
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
            onClick={onSignUp}
            disabled={isLoading}
>
            Sign Up
</Button>
          {isError && <Typography color="error">Sign up failed. Please try again.</Typography>}
</Grid>
</Grid>
    );
}