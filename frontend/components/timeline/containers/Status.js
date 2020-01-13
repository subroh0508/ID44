import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { View } from 'react-native';
import { ListItem } from 'react-native-elements';
import { RebloggedBy } from '../components/RebloggedBy';
import { TooterAvatar } from '../components/TooterAvatar';
import { StatusContent } from '../components/StatusContent';
import { Actions } from '../components/Actions';

export const Status = ({ status }) => {
  return (
    <View>
      <RebloggedBy account={ status.rebloggedBy }/>
      <ListItem
        leftAvatar={
          <TooterAvatar
            tooter={ status.tooter }
            visibility={ status.visibility }
            rebloggedBy={ status.rebloggedBy }/>
        }
        title={ <StatusContent status={ status }/> }
        subtitle={
          <Actions
            visibility={ status.visibility }
            repliesCount={ status.repliesCount }
            reblogCount={ status.reblogCount }
            favouriteCount={ status.favouriteCount }/>
        }
        bottomDivider>
      </ListItem>
    </View>
  );
};
