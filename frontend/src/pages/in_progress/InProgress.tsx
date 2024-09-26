import {InProgressProps} from "./InProgressProps.ts";
import TodoCard from "../../components/TodoCard/TodoCard.tsx";

export default function InProgress(props: InProgressProps) {
	return (
		<div className={"align-top"}>
			{props.todos.map((todo) => (
				<TodoCard key={todo.id} todo={todo} fetchAll={props.fetchAll}/>
			))}
			{props.todos.length === 0 && <div>No In Progress Todos</div>}
		</div>
	)
}