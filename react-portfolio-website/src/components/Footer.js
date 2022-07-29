import React from 'react';
import { CDBFooter, CDBBtn, CDBIcon, CDBBox } from 'cdbreact';
import styled from 'styled-components'

const Styles = styled.div`
    .footer{
        background-color: #dddae2;
    }
    
`;

export const Footer = () => {
  return (
    <Styles>
    <CDBFooter className="shadow footer">
      <CDBBox
        display="flex"
        justifyContent="between"
        alignItems="center"
        className="mx-auto py-4 flex-wrap"
        style={{ width: '80%' }}
      >
        <CDBBox display="flex" alignItems="center">
          <a href="/" className="d-flex align-items-center p-0 text-dark">
            {/* <img
              alt="logo"
              src="../assets/denkata.jpg"
              width="30px"
            /> */}
            <span className="ml-4 h5 mb-0 font-weight-bold">DB 2022</span>
          </a>
          
        </CDBBox>
        <CDBBox display="flex">
          <CDBBtn flat color="dark" className="p-2" href='https://www.linkedin.com/in/denislav-berberov/' target='_blank'>
            <CDBIcon fab icon="linkedin" />
          </CDBBtn>
          <CDBBtn flat color="dark" className="mx-3 p-2" href='https://github.com/deniksb/Portfolio-Repo-Deniksb' target='_blank'>
            <CDBIcon fab icon="github" />
          </CDBBtn>
          <CDBBtn flat color="dark" className="p-2" href='https://m.facebook.com/profile.php?id=100006839268429' target='_blank'>
            <CDBIcon fab icon="facebook" />
          </CDBBtn>
        </CDBBox>
      </CDBBox>
    </CDBFooter>
    </Styles>
  );
};