import React from 'react'
import {Container} from 'react-bootstrap'
import {Carousel as Jumbo} from 'react-bootstrap'
import styled from 'styled-components'
import forestImage from '../assets/forest-wp.jpg'
import {LogoImage} from './LogoImage'

const Styles = styled.div`
    .jumbo{
        background: url(${forestImage}) no-repeat fixed bottom;
        background-size: cover;
        color: black
;
        height: 500px;
        position: relative;
        z-index: -2;
        
    }

    .overlay{
        background-color: #000;
        opacity: 0.6;
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        z-index: -1;
    }
    .carousel-indicators li{display:none;}
    
`;

export const Jumbotron = () => (

    <Styles>
        <Jumbo controls={false} indicators={false} fluid className="jumbo" >
            <div className='overlay'></div>
            <Container>
                <h1>Denis Berberov</h1>
                <p>Software Engineer</p>
                <LogoImage />
            </Container>
        </Jumbo>
    </Styles>


)

