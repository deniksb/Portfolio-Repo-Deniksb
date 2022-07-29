import React from 'react'
import { Nav, Navbar } from 'react-bootstrap'
import styled from 'styled-components'

const Styles = styled.div`
    .navbar{
        background-color: #0b111d;
        
    }

    .navbar-brand, .navbar-nav, .nav-link{
        color: #bbb !important;
        

    &:hover{
        color:white;
    }

    button.bg-light{
        background-color: #dddae2 !important;
    }
    
}

`;

export const NavigationBar = () => (
    <Styles>
        <Navbar expand="lg" className='sticky-nav' >
            <Navbar.Brand href="/"><h2><b>DB</b></h2></Navbar.Brand>
            <Navbar.Toggle aria-controls='basic-navbar-nav' className='bg-light'/>
            <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="ml-auto">
                    <Nav.Item><Nav.Link href="/">Home</Nav.Link></Nav.Item>
                    <Nav.Item><Nav.Link href="/skills">Skills</Nav.Link></Nav.Item>
                    <Nav.Item><Nav.Link href="/experience">Experience</Nav.Link></Nav.Item>
                    <Nav.Item><Nav.Link href="/links">Links</Nav.Link></Nav.Item>
                </Nav>
            </Navbar.Collapse>
        </Navbar>
    </Styles>
)