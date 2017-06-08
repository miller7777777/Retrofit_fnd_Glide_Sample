package android2.levelup.ru.lesson08_refactoring_and_retrofit;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.RequestManager;

import java.util.List;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.ViewHolder> {
    private final List<Car> cars;
    private final RequestManager imageRequestManager;
    private CarClickListener listener;

    public CarsAdapter(List<Car> cars, RequestManager imageRequestManager, CarClickListener listener) {
        this.cars = cars;
        this.imageRequestManager = imageRequestManager;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_car_item, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Car car = cars.get(position);
        holder.bindTo(car);
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView nameView;
        final TextView vinView;

        private Car car;

        public ViewHolder(View itemView, final CarClickListener listener) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            nameView = (TextView) itemView.findViewById(R.id.nameView);
            vinView = (TextView) itemView.findViewById(R.id.vinView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCarClick(car);
                }
            });
        }

        public void bindTo(Car car) {
            this.car = car;
            nameView.setText(car.getName());
            vinView.setText(car.getVin());


            imageRequestManager.load(car.getImage()).into(imageView);
            // TODO: display image
        }
    }

    public interface CarClickListener {
        void onCarClick(Car car);
    }
}
