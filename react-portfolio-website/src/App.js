import './App.css';
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import { Home } from './components/Home'
import { Skills } from './components/Skills'
import { Experience } from './components/Experience'
import { Links } from './components/Links'
import { NoMatch } from './components/NoMatch'
import { Layout } from './components/Layout'
import { NavigationBar } from './components/NavigationBar'
import { Jumbotron } from './components/Jumbotron'
import { Footer } from './components/Footer'

function App() {
  return (
    <>
      <NavigationBar />
      <Jumbotron />
      <Layout>
        <Router>
          <Routes>
            <Route exact path="/" element={<Home />} />
            <Route path="/skills" element={<Skills />} />
            <Route path="/experience" element={<Experience />} />
            <Route path="/links" element={<Links />} />
            <Route element={<NoMatch />} />
          </Routes>
        </Router>
      </Layout>
      <br></br>
      <Footer />
    </>
  );
}

export default App;
