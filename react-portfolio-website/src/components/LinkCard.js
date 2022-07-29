import Card from 'react-bootstrap/Card'
import styled from 'styled-components'


const Styles = styled.div`
  .link-card{
    background-color:#dddae2;
    color: #0b111d;
  }
`;

export const LinkCard = (data) => (
    <Styles>
    <Card style={{ width: '18rem'}} className="link-card">
    <Card.Img variant="top" src={data["data"]["img"]} />
      <Card.Body>
        <Card.Title>{data["data"]["title"]}</Card.Title>
        <Card.Text>{data["data"]["content"]}</Card.Text>
        <Card.Link href={data["data"]["link"]} target="_blank">Click here</Card.Link>
      </Card.Body>
    </Card>
    </Styles>
)

