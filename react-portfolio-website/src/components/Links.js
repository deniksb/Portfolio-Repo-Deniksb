import React from 'react'
import {LinkCard} from './LinkCard'
import styled from 'styled-components'
import fiverr from '../assets/fiverr.png'
import github from '../assets/github.png'
import linkedin from '../assets/linkedin.png'

const Styles = styled.div`
    .flexbox-container{
        display: flex;
        justify-content: center;
        flex-wrap: wrap;
        gap: 1rem;
    }
`;

export const Links = () => (
    <div>
        <Styles>
        <h1><b>Links</b></h1>
        <br></br>
        <div className='flexbox-container'>
        <LinkCard data={{title: 'LinkedIn', content: "My profile!", link: 'https://www.linkedin.com/in/denislav-berberov/',img: linkedin}}/>
        <LinkCard data={{title: 'GitHub', content: "My porfolio!", link: 'https://github.com/deniksb/Portfolio-Repo-Deniksb',img: github}}/>
        <LinkCard data={{title: 'Fiverr', content: "My freelancing!", link: 'https://www.fiverr.com/deniksb',img: fiverr}}/>
        </div>
        </Styles>
    </div>
)