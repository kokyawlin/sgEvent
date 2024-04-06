import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { Grid, Typography, Button, TextField } from '@mui/material';
import { useLoginMutation } from '../services/auth.service';
import { navigate } from 'gatsby';
import Header from "../components/Header/index";
import { setCredentials } from '../state/auth/slice'; // 确保这个导入路径是正确的
import { Link } from 'gatsby';

export default function LoginPage() {
    const dispatch = useDispatch();
    // 注意这里使用emailAddress来反映数据库字段
    const [emailAddress, setEmailAddress] = useState('');
    const [password, setPassword] = useState('');
    const [login, { isLoading, isError }] = useLoginMutation();
 
    const onLogin = async () => {
        try {
            // 确保发送的字段名与数据库/后端期望的匹配
            const userData = await login({
                emailAddress: emailAddress, // 这里使用camelCase，假设后端能正确映射到snake_case
                password: password,
            }).unwrap();
            dispatch(setCredentials(userData)); // 直接传递userData
            navigate('/home');
        } catch (err) {
            console.error('Failed to login:', err);
        }
    };
 
    return (
        <React.Fragment>
            <Header />
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
                        onClick={onLogin}
                        disabled={isLoading}
                    >
                        Login
                    </Button>
                    {isError && <Typography color="error">Login failed. Please try again.</Typography>}
                    
                    {/* 在这里添加注册链接 */}
                    <Typography style={{ marginTop: 20 }}>
                    Don't have SG EventHub account? 
                    <span
                        style={{ textDecoration: 'underline', cursor: 'pointer' }}
                        onClick={() => navigate('/signup')}
                    >
                        Sign Up now
                    </span>
                    </Typography>
             
                </Grid>
            </Grid>
        </React.Fragment>
    );
}
