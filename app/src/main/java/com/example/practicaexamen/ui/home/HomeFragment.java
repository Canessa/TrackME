package com.example.practicaexamen.ui.home;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.practicaexamen.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.File;
import java.io.IOException;
public class HomeFragment extends Fragment {
    //Una referencia al storage de firebase...
    private StorageReference storageReference;
    //Atributos
    private ImageView imagen;
    private Button btCargar;
    private FirebaseAuth mAuth;
    private EditText etArchivo;
    private Button btBuscar;
    private Button btDescargar;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        btCargar = root.findViewById(R.id.btCargar);
        imagen = root.findViewById(R.id.imagen);
        etArchivo = root.findViewById(R.id.etArchivo);
        btDescargar = root.findViewById(R.id.btDescargar);
        btBuscar = root.findViewById(R.id.btBuscar);
        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar();
            }
        });
        btCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargar();
            }
        });
        btDescargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descargar();
            }
        });
        mAuth = FirebaseAuth.getInstance();
        return root;
    }

    private void buscar() {
        //Solicito al s.o. abrir un documento...
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");  //Los documentos a abrir son de tipo imagen...
        //Inicio un activity que me devolvera algo...
        startActivityForResult(intent,333);
    }
    //Para mantener la ruta del recurso a presentar o subir... -archivo-
    private Uri uri;
    //Este metodo se ejecuta cuando regresa del Open Document...
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        uri=data.getData();  //Obtengo la ruta del archivo seleccionado
        imagen.setImageURI(uri);  //Cargo en la imagen del App el archivo seleccionado...
    }
    private void cargar() {
        //Recupero lo escrito en el app, donde esta el nombre del archivo a subir
        FirebaseUser user = mAuth.getCurrentUser();
        String User = user.getEmail();
        //etUser = User;
        //Fijo la referencia directamente al archivo en el Firebase... donde quedara
        storageReference = FirebaseStorage.getInstance().getReference().child(User);
        storageReference.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getContext(), "Se cargo", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Error de carga", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void descargar() {
        //Recupero lo escrito en el app, donde esta el nombre del archivo
        String archivo = etArchivo.getText().toString();
        //Fijo la referencia directamente al archivo en el Firebase...
        storageReference = FirebaseStorage.getInstance().getReference().child(archivo);
        //voy a crear un archivo temporal para descargar la imagen...
        File file=null;
        try {
            file = File.createTempFile("images","jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        final File finalFile = file;
        storageReference.getFile(file)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        String ruta = finalFile.getAbsolutePath();
                        imagen.setImageBitmap(BitmapFactory.decodeFile(ruta));
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error de descarga", Toast.LENGTH_SHORT).show();
            }
        });
    }
}