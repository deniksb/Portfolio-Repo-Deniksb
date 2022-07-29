import React from 'react'
import { ExperienceCard } from './ExperienceCard'

export const Experience = () => (
    <div>
        <h1>Experience</h1>
        <br></br>
        <ExperienceCard data={{duration: "Apr 2022 - Aug 2022", 
        title: "Trading 212 Web Development Internship", 
        content: "During this time I worked on several projects using technologies such as Java Spring Boot, MySQL, PHP, RabbitMQ, Docker. Some of these can be seen in my github repository. The main focus of the program was backend development with Java but I also had hands on experience with database design, dev ops and frontend development." }}
        />
        <br></br>
        <ExperienceCard data={{duration: "Feb 2020 - Apr 2022", 
        title: "Freelance Game Developer on Fiverr", 
        content: "I have worked as a freelancer on Fiverr and gained experience in the field of Game Development as I cooperated with clients in order to develop PC and Android games that suit their liking. I primarily used HTML, CSS, JavaScript and Java for these projects." }}
        />
        <br></br>
        <ExperienceCard data={{duration: "May 2021 - Jun 2021", 
        title: "Web Developer for ND Group 2020 EOOD", 
        content: "I worked collaboratively in a group of 2 to develop and deploy a website for ND Group 2020 EOOD which is a house management firm in Sofia, Bulgaria. We used HTML, CSS, Bootstrap and PHP to create the responsive website and hosted it with Hostinger." }}
        />

    </div>
)