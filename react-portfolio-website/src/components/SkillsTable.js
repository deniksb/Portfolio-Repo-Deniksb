import Card from 'react-bootstrap/Card'
import styled from 'styled-components'
import {Table} from 'react-bootstrap'

const Styles = styled.div`
.skills-table{
    background-color: #dddae2;
}
`;

export const SkillsTable = () => (
    <Styles>
  <Table className='skills-table' striped bordered hover>
      <thead>
        <tr>
          <th>#</th>
          <th>Technology</th>
          <th>Skill Level</th>
          
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1</td>
          <td>Java</td>
          <td>Intermediate</td>
          
        </tr>
        <tr>
          <td>2</td>
          <td>Java Spring Boot</td>
          <td>Intermediate</td>
          
        </tr>
        <tr>
          <td>3</td>
          <td>JavaScript</td>
          <td>Intermediate</td>
        </tr>
        <tr>
          <td>4</td>
          <td>MySQL</td>
          <td>Intermediate</td>
        </tr>
        <tr>
          <td>5</td>
          <td>HTML</td>
          <td>Intermediate</td>
        </tr>
        <tr>
          <td>6</td>
          <td>CSS</td>
          <td>Intermediate</td>
        </tr>
        <tr>
          <td>7</td>
          <td>Bootstrap</td>
          <td>Intermediate</td>
        </tr>
        <tr>
          <td>8</td>
          <td>React</td>
          <td>Beginner</td>
        </tr>
        <tr>
          <td>9</td>
          <td>PHP</td>
          <td>Beginner</td>
        </tr>
        <tr>
          <td>10</td>
          <td>Python</td>
          <td>Beginner</td>
        </tr>
        <tr>
          <td>11</td>
          <td>SCRUM</td>
          <td>Beginner</td>
        </tr>
        <tr>
          <td>12</td>
          <td>Agile</td>
          <td>Beginner</td>
        </tr>
      </tbody>
    </Table>
    </Styles>
)