import React from "react";
import MessageBar from "./MessageBar";
import "./styles/MessageBoard.css";

interface MessageBoardProps {
  name: string;
  messages: Array<string>;
}

const MessageBoard: React.FC<MessageBoardProps> = ({ name, messages }) => {
  return (
    <div className="message-board">
      <h2>{name}</h2>
      <div className="message-list">
        {messages.map((el) => {
          return <MessageBar key={el} message={el}></MessageBar>;
        })}
      </div>
    </div>
  );
};

export default MessageBoard;
