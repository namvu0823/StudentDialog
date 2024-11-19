package vn.edu.hust.studentman

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class StudentAdapter(val students: MutableList<StudentModel>): RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
  class StudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
    val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)
    val imageEdit: ImageView = itemView.findViewById(R.id.image_edit)
    val imageRemove: ImageView = itemView.findViewById(R.id.image_remove)

  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_student_item,
       parent, false)
    return StudentViewHolder(itemView)
  }

  override fun getItemCount(): Int = students.size

  override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
    val student = students[position]

    holder.textStudentName.text = student.studentName
    holder.textStudentId.text = student.studentId
    holder.imageEdit.setOnClickListener{
      val dialogView = LayoutInflater.from(holder.itemView.context).inflate(R.layout.edit_student, null)

      val edithoten = dialogView.findViewById<EditText>(R.id.text_student_name)
      val editid =dialogView.findViewById<EditText>(R.id.text_student_id)

      edithoten.setText(student.studentName)
      editid.setText(student.studentId)

      AlertDialog.Builder(holder.itemView.context)
        .setTitle("Edit student")
        .setView(dialogView)
        .setPositiveButton("OK"){_,_->
          val hoten = edithoten.text.toString()
          val id = editid.text.toString()
          if(hoten.isNotEmpty()&&id.isNotEmpty()){
            students[position]=student.copy(
              studentName = hoten,
              studentId =id
            )
            notifyItemChanged(position)
          }
        }
        .setNegativeButton("Cancel",null)
        .show()

    }
    holder.imageRemove.setOnClickListener{
      val deleteStudent=students[position]
      val deletePosition=position

      AlertDialog.Builder(holder.itemView.context)
        .setTitle("Delete student")
        .setMessage("Are you sure to delete this student?")
        .setPositiveButton("Ok"){_,_->
          students.removeAt(deletePosition)
          notifyItemRemoved(deletePosition)
          notifyItemRangeChanged(deletePosition,students.size)
          Snackbar.make(holder.itemView,"Student ${deleteStudent.studentName} removed",Snackbar.LENGTH_LONG)
            .setAction("Undo"){
              students.add(deletePosition,deleteStudent)
              notifyItemInserted(deletePosition)
              notifyItemRangeChanged(deletePosition,students.size)
            }.show()
        }
        .setNegativeButton("Cancel",null)
        .show()

    }
  }

}