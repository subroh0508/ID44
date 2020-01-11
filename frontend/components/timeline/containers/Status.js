import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { ListItem } from 'react-native-elements';
import { TooterAvatar } from '../components/TooterAvatar';
import { StatusContent } from '../components/StatusContent';
import { Actions } from '../components/Actions';

export const Status = ({ status }) => {
  
  return (
    <ListItem
      leftAvatar={
        <TooterAvatar
          tooter={ status.tooter }
          visibility={ status.visibility }/>
      }
      title={ <StatusContent status={ status }/> }
      subtitle={
        <Actions
          repliesCount={ status.repliesCount }
          reblogCount={ status.reblogCount }
          favouriteCount={ status.favouriteCount }/>
      }
      bottomDivider>
    </ListItem>
  );
};
