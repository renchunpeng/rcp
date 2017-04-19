/**
 * Created by semantic on 2017/2/4.
 */
import React, { Component, PropTypes } from 'react';
import { connect } from 'dva';

function Material() {
  return (
    <div>
      show message 20170204
    </div>
  )
}

Material.propTypes = {};

export default connect()(Material);
