import React from 'react';

function TextError (props) {
  return <div className='text-red-400 absolute -bottom-6 text-sm'>{props.children}</div>
}

export default TextError;