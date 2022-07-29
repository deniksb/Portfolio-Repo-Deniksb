import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import styled from 'styled-components'

const Styles = styled.div`
    .experience-card{
        background-color:#dddae2;
        color: #0b111d;
    }
`;


export const ExperienceCard = (data) => {
    return (
    <Styles>
    <Card className='experience-card'>
      <Card.Header><b>{data["data"]["title"]}</b></Card.Header>
      <Card.Body>
        <Card.Title><i>{data["data"]["duration"]}</i></Card.Title>
        <Card.Text>
        {data["data"]["content"]}
        </Card.Text>
      </Card.Body>
    </Card>
    </Styles>
  );
}
