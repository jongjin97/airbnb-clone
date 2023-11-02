import React, { useEffect, useRef, useState } from 'react';
import { Client, CompatClient, Message } from '@stomp/stompjs';
import Avatar from 'src/components/Avatar';
import { ChatRoom, chatMessage } from 'src/interface/chat';
import { getMyChatRoom } from 'src/api/chat.api';
import { useAppSelector } from 'src/app/hooks';
import ChatBox from './ChatBox';
import { useNavigate } from 'react-router-dom';

const ChatComponent = () => {
  const [chatRoom, setChatRoom] = useState<ChatRoom[]>();
  const [currChat, setCurrChat] = useState<ChatRoom>();
  const user = useAppSelector((state) => state.auth.user);
  const router = useNavigate();

  const handleClickUser = (chat: ChatRoom) => {
    setCurrChat(chat);
    router('/messages/'+chat?.id);  
  }

  useEffect(() => {
    getMyChatRoom().then((res) => {
      console.log(res.data.response);
      setChatRoom(res.data.response);
    })
  },[currChat])

  return (
    <div className="flex flex-row justify-between bg-white pt-24 gap-8">
      <div className="flex flex-col w-2/5 border-r-2 overflow-y-auto">
        <div className="border-b-2 py-4 px-2">
          <input
            type="text"
            placeholder="search chatting"
            className="py-2 px-2 border-2 border-gray-200 rounded-2xl w-full"
          />
        </div>

        {chatRoom?.map((chatroom) => (
          <div
          className="flex flex-row py-4 px-2 justify-center items-center border-b-2"
          onClick={() => {
            handleClickUser(chatroom);
            console.log(chatroom);
          }}
        >
          <div className="w-1/4">
            <Avatar src={null} size={50}/>
          </div>
          <div className="w-full">
            <div className="text-lg font-semibold">{chatroom.user.id === user?.id ? chatroom.user2.name : chatroom.user.name}</div>
          </div>
        </div>
        ))}

        <div
          className="flex flex-row py-4 px-2 justify-center items-center border-b-2"
        >
          <div className="w-1/4">
            <Avatar src={null} size={50}/>
          </div>
          <div className="w-full">
            <div className="text-lg font-semibold">Luis1994</div>
          </div>
        </div>
      </div>
      {currChat && <ChatBox currChat={currChat} />}
    </div>
     );
};

export default ChatComponent;
