attribute vec3 position;

varying vec4 color;

void main()
{
    gl_Position = vec4(position, 1.0);
    color = vec4(0.5, 0.5, 0.5, 1.0);
}
