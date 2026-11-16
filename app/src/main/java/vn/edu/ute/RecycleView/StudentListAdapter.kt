package vn.edu.ute.RecycleView

// Giữ nguyên dòng package của bạn ở trên cùng
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import vn.edu.ute.RecycleView.databinding.ItemStudentBinding

// Nhấn Alt + Enter vào chữ ItemStudentBinding bên dưới để tự động import

class StudentListAdapter(
    private val onStudentClick: (Student) -> Unit,
    private val onDeleteClick: (Student) -> Unit
) : ListAdapter<Student, StudentListAdapter.StudentViewHolder>(StudentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class StudentViewHolder(
        private val binding: ItemStudentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(student: Student) {
            binding.tvName.text = student.name
            binding.tvMssv.text = "MSSV: ${student.mssv}"
            binding.tvGpa.text = "GPA: ${student.gpa}"
            binding.tvAvatar.text = student.name.take(1)
            binding.tvAvatar.background.setTint(student.avatarColor)

            binding.root.setOnClickListener { onStudentClick(student) }
            binding.btnDelete.setOnClickListener { onDeleteClick(student) }
        }
    }

    class StudentDiffCallback : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem == newItem
        }
    }
}