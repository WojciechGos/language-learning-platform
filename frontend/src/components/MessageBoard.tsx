import React from "react";
import MessageBar from "./MessageBar";
import "./styles/MessageBoard.css";
import { NavLink } from "react-router-dom";

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
          return (
            <NavLink key={el} to={`/classes/${el}`} className="no-style">
              <MessageBar key={el} message={el}></MessageBar>
            </NavLink>
          );
        })}
      </div>
    </div>
  );
};

export default MessageBoard;
