import React from 'react';

const AuthForm = ({ type }) => {
  return (
    <form className="auth-form">
      {type === 'signup' && (
        <input type="text" placeholder="Enter your full name" />
      )}
      <input type="email" placeholder="Enter your email" />
      <input type="password" placeholder={type === 'signup' ? 'Create a password' : 'Enter your password'} />
      {type === 'signup' && (
        <input type="password" placeholder="Confirm your password" />
      )}
      <button type="submit">{type === 'signup' ? 'Sign Up' : 'Login'}</button>

      {type === 'login' && (
        <>
          <hr />
          <button className="google">Sign in with Google</button>
          <button className="facebook">Sign in with Facebook</button>
        </>
      )}
    </form>
  );
};

export default AuthForm;
