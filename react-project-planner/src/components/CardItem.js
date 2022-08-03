import React from 'react'
import ListGroup from 'react-bootstrap/ListGroup';

export const CardItem = (data) => {
    return (
        <ListGroup.Item>{data["data"]["content"]}</ListGroup.Item>
  );
}
