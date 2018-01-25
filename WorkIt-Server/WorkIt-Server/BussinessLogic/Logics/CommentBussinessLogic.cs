using System.Collections.Generic;
using System.Linq;
using WorkIt_Server.Models;
using WorkIt_Server.Models.Context;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.BLL
{
    public class CommentBussinessLogic
    {
        private WorkItDbContext db;

        public CommentBussinessLogic(WorkItDbContext db)
        {
            this.Db = db;
        }

        public WorkItDbContext Db
        {
            get
            {
                return this.db;
            }
            set
            {
                this.db = value;
            }
        }

        public void CreateComment(CommentDTO comment)
        {
            var commentToBeInserted = new Comment
            {
                AuthorId = comment.AuthorId,
                Message = comment.Message,
                TaskId = comment.TaskId,
            };

            Db.Comments.Add(commentToBeInserted);
            Db.SaveChanges();
        }

        public IEnumerable<CommentDTO> GetCommentsByTaskId(int taskId)
        {
            return Db.Comments
                .Where(c => c.TaskId == taskId)
                .Select(c => new CommentDTO
                {
                    AuthorId = c.AuthorId,
                    Message = c.Message,
                    TaskId = c.TaskId
                });
        }
        
        public void DeleteCommentById(int commentId)
        {
            var commentToBeRemoved = Db.Comments.FirstOrDefault(c => c.CommentId == commentId);

            Db.Comments.Remove(commentToBeRemoved);
            Db.SaveChanges();
        }

        public void DeleteCommentsByTaskId(int taskId)
        {
            var commentsToBeRemoved = Db.Comments.Where(c => c.TaskId == taskId).ToList();

            Db.Comments.RemoveRange(commentsToBeRemoved);
            Db.SaveChanges();
        }
    }
}