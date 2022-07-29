import React from 'react';
import denkataImage from '../assets/denkata.jpg'
import styled from 'styled-components'

const Styles = styled.div`
   .logo-image{
    width: 300px !important;
    height:300px !important;
    border-radius: 50%;
   }

   .image-container{
    text-align:center;
   }
    
`;

export const LogoImage = () => (
  // Import result is the URL of your image
  
    <Styles><div className='image-container'><img src={denkataImage} alt="Denkata" className='logo-image' /></div></Styles>
  
)
