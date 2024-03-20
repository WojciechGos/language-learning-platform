import { useState } from "react";
import "./styles/AssistantBar.css";

function AssistantBar() {
  const [inputValue, setInputValue] = useState("");

  const handleInputChange = (e: any) => {
    setInputValue(e.target.value);
  };

  return (
    <div className="bar-tile">
      <label>
        <input
          type="text"
          value={inputValue}
          onChange={handleInputChange}
          className="input-text-assistant"
          placeholder="Type a question..."
        />
      </label>
    </div>
  );
}

export default AssistantBar;
