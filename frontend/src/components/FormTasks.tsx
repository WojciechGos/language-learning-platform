import "./styles/FormScenario.css";
import Task from "./Task";

function FormTasks() {
  const handleSubmit = (e: any) => {
    e.preventDefault();
    console.log("Submitted value:");
  };

  return (
    <>
      <Task />
      <Task />
      <Task />
      <form onSubmit={handleSubmit}>
        <button type="submit" className="button-send">
          GENERUJ!
        </button>
      </form>
    </>
  );
}

export default FormTasks;
