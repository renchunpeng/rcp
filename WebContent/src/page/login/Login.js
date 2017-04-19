/**
 * Created by semantic on 2017/1/24.
 */

import React, { Component, PropTypes } from 'react';
import { connect } from 'dva';

function Login() {
  return (
    <div>
      show goodMessage!
    </div>
  )
}

Login.propTypes = {};

export default connect()(Login);
