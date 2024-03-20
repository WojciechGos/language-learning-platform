import { useState } from "react";
import "./styles/FormScenario.css";
import { useNavigate } from "react-router-dom";

function FormLesson() {
  const navigate = useNavigate();
  const [topicValue, setTopicValue] = useState("");

  const handleTopicChange = (e: any) => {
    setTopicValue(e.target.value);
  };

  const handleSubmit = (e: any) => {
    e.preventDefault();
    console.log("Submitted value:", topicValue);
    navigate("/");
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="form-tile">
        <label className="bigText">
          Enter topic:
          <input
            type="text"
            value={topicValue}
            onChange={handleTopicChange}
            className="input-text"
          />
        </label>
      </div>
      <button type="submit" className="button-send">
        GENERUJ!
      </button>
    </form>
  );
}

export default FormLesson;
