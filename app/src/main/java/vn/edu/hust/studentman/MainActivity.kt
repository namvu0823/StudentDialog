package vn.edu.hust.studentman

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val students = mutableListOf(
      StudentModel("Nguyễn Văn An", "SV001"),
      StudentModel("Trần Thị Bảo", "SV002"),
      StudentModel("Lê Hoàng Cường", "SV003"),
      StudentModel("Phạm Thị Dung", "SV004"),
      StudentModel("Đỗ Minh Đức", "SV005"),
      StudentModel("Vũ Thị Hoa", "SV006"),
      StudentModel("Hoàng Văn Hải", "SV007"),
      StudentModel("Bùi Thị Hạnh", "SV008"),
      StudentModel("Đinh Văn Hùng", "SV009"),
      StudentModel("Nguyễn Thị Linh", "SV010"),
      StudentModel("Phạm Văn Long", "SV011"),
      StudentModel("Trần Thị Mai", "SV012"),
      StudentModel("Lê Thị Ngọc", "SV013"),
      StudentModel("Vũ Văn Nam", "SV014"),
      StudentModel("Hoàng Thị Phương", "SV015"),
      StudentModel("Đỗ Văn Quân", "SV016"),
      StudentModel("Nguyễn Thị Thu", "SV017"),
      StudentModel("Trần Văn Tài", "SV018"),
      StudentModel("Phạm Thị Tuyết", "SV019"),
      StudentModel("Lê Văn Vũ", "SV020")
    )

    val studentAdapter = StudentAdapter(students)

    findViewById<RecyclerView>(R.id.recycler_view_students).run {
      adapter = studentAdapter
      layoutManager = LinearLayoutManager(this@MainActivity)
    }


    findViewById<Button>(R.id.btn_add_new).setOnClickListener{
      val dialogView = LayoutInflater.from(this).inflate(R.layout.add_student, null)
      val addHoten=dialogView.findViewById<EditText>(R.id.text_student_name)
      val addId=dialogView.findViewById<EditText>(R.id.text_student_id)

      AlertDialog.Builder(this)
        .setTitle("ADD STUDENT")
        .setView(dialogView)
        .setPositiveButton("OK"){_,_ ->

          val hoten =addHoten.text.toString()
          val id =addId.text.toString()

          if(hoten.isNotEmpty()&&id.isNotEmpty()) {
            if(students.any{it.studentId==id}){
              Log.v("TAG","Student ID is already exists")
            }
            else {
              students.add(StudentModel(hoten, id))
              studentAdapter.notifyItemInserted(students.size - 1)
              Log.v("TAG", "Add new student: $hoten-$id")
            }
          }
        }
        .setNegativeButton("Cancel",null)
        .show()
    }

  }

}